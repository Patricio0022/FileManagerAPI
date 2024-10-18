import './styles/main.css';
import { AvatarIcon } from './components/AvatarIcon';
import  {InputButton} from './components/InputButton'
import { useEffect } from 'react';



export function App() {
  return (
    <div className='container p-20'>
     
      <div className='flex flex-col items-center justify-center p-none m-none rounded-sm bg-white h-[750px] w-full'>
      <AvatarIcon/>
      <div className='p-7 h-[200px] flex justify-center w-[700px]'>
      <InputButton />
      </div>
      </div>

    </div>
    
  );
}

